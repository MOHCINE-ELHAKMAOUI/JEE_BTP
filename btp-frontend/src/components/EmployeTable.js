import { useEffect, useState } from "react";
import { affecterEmploye } from "../services/projetService";
import { getEmployes } from "../services/employeService";

export default function EmployeTable({ projetId }) {
    const [employes, setEmployes] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");

    const fetchEmployes = () => {
        setLoading(true);
        getEmployes()
            .then((res) => {
                setEmployes(res.data);
                setLoading(false);
            })
            .catch((err) => {
                console.error(err);
                setError("Erreur lors du chargement des employés");
                setLoading(false);
            });
    };

    useEffect(() => {
        fetchEmployes();
    }, []);

    if (loading) return <p>Chargement des employés...</p>;
    if (error) return <p className="text-red-600">{error}</p>;

    return (
        <div className="bg-white p-4 rounded shadow mt-4">
            <h2 className="text-xl font-bold mb-4">Liste des employés</h2>

            <table className="w-full border">
                <thead className="bg-gray-200">
                    <tr>
                        <th className="border p-2">Nom</th>
                        <th className="border p-2">Rôle</th>
                        <th className="border p-2">Disponible</th>
                        <th className="border p-2">Action</th>
                    </tr>
                </thead>
                <tbody>
                    {employes.map((e) => (
                        <tr key={e.id}>
                            <td className="border p-2">{e.nom}</td>
                            <td className="border p-2">{e.role}</td>
                            <td className="border p-2">
                                {e.disponible ? "Oui" : "Non"}
                            </td>

                            <td className="border p-2">
                                <button
                                    className={`px-2 py-1 rounded text-white ${
                                        !projetId || !e.disponible
                                            ? "bg-gray-400 cursor-not-allowed"
                                            : "bg-blue-500 hover:bg-blue-600"
                                    }`}
                                    disabled={!projetId || !e.disponible}
                                    onClick={() => {
                                        if (!projetId) {
                                            alert("Veuillez créer un projet d'abord");
                                            return;
                                        }

                                        if (!e.disponible) {
                                            alert("Cet employé n'est pas disponible");
                                            return;
                                        }

                                        // Ensure projetId is a number
                                        const pid = typeof projetId === 'string' ? parseInt(projetId, 10) : projetId;
                                        const eid = typeof e.id === 'string' ? parseInt(e.id, 10) : e.id;
                                        
                                        if (!pid || isNaN(pid)) {
                                            alert("Erreur: ID de projet invalide");
                                            return;
                                        }
                                        
                                        if (!eid || isNaN(eid)) {
                                            alert("Erreur: ID d'employé invalide");
                                            return;
                                        }

                                        affecterEmploye(pid, eid)
                                            .then(() => {
                                                alert("Employé affecté avec succès !");
                                                // Refresh the employee list to show updated availability
                                                fetchEmployes();
                                                // Trigger event to refresh DevisCard
                                                window.dispatchEvent(new Event('employeeAssigned'));
                                            })
                                            .catch((err) => {
                                                console.error("Error assigning employee:", err);
                                                const errorMessage = err.response?.data?.message 
                                                    || err.response?.statusText
                                                    || err.message 
                                                    || "Erreur lors de l'affectation";
                                                alert(`Erreur: ${errorMessage}`);
                                            });
                                    }}
                                >
                                    {!e.disponible ? "Non disponible" : "Affecter"}
                                </button>
                            </td>

                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}
