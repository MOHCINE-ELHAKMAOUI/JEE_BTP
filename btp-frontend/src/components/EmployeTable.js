// export default function EmployeTable() {
//     return (
//         <div className="bg-white p-4 rounded-xl shadow">
//             <h3 className="font-bold mb-3">👷 Employés</h3>
//             <table className="w-full text-sm">
//                 <thead>
//                 <tr className="bg-gray-200">
//                     <th className="p-2">Nom</th>
//                     <th className="p-2">Rôle</th>
//                     <th className="p-2">Statut</th>
//                     <th className="p-2">Email</th>
//                 </tr>
//                 </thead>
//                 <tbody>
//                 <tr className="border-b">
//                     <td className="p-2">Nom</td>
//                     <td className="p-2">Rôle</td>
//                     <td className="p-2">Statut</td>
//                     <td className="p-2 text-green-600">Disponible</td>
//                 </tr>
//                 </tbody>
//             </table>
//         </div>
//     );
// }

import { useEffect, useState } from "react";
import { affecterEmploye } from "../services/projetService";
import { getEmployes } from "../services/employeService";

export default function EmployeTable() {
    const [employes, setEmployes] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");

    useEffect(() => {
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

                            <td>
                                <button
                                    className="bg-blue-500 text-white px-2 py-1 rounded"
                                    onClick={() => {
                                        const projetId = localStorage.getItem("projetActifId");
                                        if (!projetId) {
                                            alert("Veuillez créer un projet d'abord");
                                            return;
                                        }

                                        affecterEmploye(projetId, e.id)
                                            .then(() => {
                                                alert("Employé affecté !");
                                                window.location.reload();
                                            })
                                            .catch(err => {
                                                console.error(err);
                                                alert("Erreur lors de l'affectation");
                                            });
                                    }}
                                >
                                    Affecter
                                </button>
                            </td>

                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}
