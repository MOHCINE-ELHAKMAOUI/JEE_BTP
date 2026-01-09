import { useEffect, useState } from "react";
import { getDevisByProjet } from "../services/devisService";

export default function DevisCard({ projetId }) {
    const [devis, setDevis] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const fetchDevis = () => {
        if (!projetId) {
            setDevis(null);
            setError(null);
            return;
        }

        setLoading(true);
        setError(null);
        
        getDevisByProjet(projetId)
            .then((res) => {
                if (res.data) {
                    setDevis(res.data);
                } else {
                    setError("Aucun devis trouvé pour ce projet");
                }
                setLoading(false);
            })
            .catch((err) => {
                console.error("Error fetching devis:", err);
                if (err.response?.status === 404) {
                    setError("Aucun devis trouvé pour ce projet. Le devis devrait être créé automatiquement lors de la création du projet.");
                } else {
                    setError("Erreur lors du chargement du devis: " + (err.response?.data?.message || err.message));
                }
                setLoading(false);
                setDevis(null);
            });
    };

    useEffect(() => {
        fetchDevis();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [projetId]);

    // Refresh when employee is assigned
    useEffect(() => {
        const handleRefresh = () => {
            setTimeout(() => {
                fetchDevis();
            }, 500); // Small delay to ensure backend has updated
        };
        
        window.addEventListener('employeeAssigned', handleRefresh);
        return () => window.removeEventListener('employeeAssigned', handleRefresh);
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [projetId]);

    if (!projetId) {
        return (
            <div className="bg-white p-4 rounded shadow mt-4">
                <p className="text-gray-500">Sélectionnez un projet pour voir le devis</p>
            </div>
        );
    }

    if (loading) {
        return (
            <div className="bg-white p-4 rounded shadow mt-4">
                <p>Chargement du devis...</p>
            </div>
        );
    }

    if (error) {
        return (
            <div className="bg-white p-4 rounded shadow mt-4">
                <p className="text-red-600">{error}</p>
            </div>
        );
    }

    if (!devis) {
        return (
            <div className="bg-white p-4 rounded shadow mt-4">
                <p className="text-gray-500">Aucun devis trouvé pour ce projet</p>
            </div>
        );
    }

    const employes = devis.projet?.employes || [];

    return (
        <div className="bg-white p-4 rounded shadow mt-4">
            <h2 className="text-xl font-bold mb-4">
                Devis du projet #{devis.projet?.id || projetId}
            </h2>

            <div className="mb-4">
                <p className="mb-1">
                    <span className="font-semibold">Date :</span> {devis.dateCreation ? (typeof devis.dateCreation === 'string' ? new Date(devis.dateCreation).toLocaleDateString() : devis.dateCreation) : 'N/A'}
                </p>

                <p className="text-lg font-bold mt-2">
                    <span className="font-semibold">Montant total :</span> {typeof devis.montantTotal === 'number' ? devis.montantTotal.toFixed(2) : devis.montantTotal} DH
                </p>
            </div>

            <div className="mt-4 border-t pt-4">
                <h3 className="text-lg font-semibold mb-3">Employés affectés au projet</h3>
                {employes.length === 0 ? (
                    <p className="text-gray-500 italic">Aucun employé affecté pour le moment</p>
                ) : (
                    <table className="w-full border">
                        <thead className="bg-gray-100">
                            <tr>
                                <th className="border p-2 text-left">Nom</th>
                                <th className="border p-2 text-left">Rôle</th>
                                <th className="border p-2 text-left">Statut</th>
                            </tr>
                        </thead>
                        <tbody>
                            {employes.map((employe) => (
                                <tr key={employe.id}>
                                    <td className="border p-2">{employe.nom}</td>
                                    <td className="border p-2">{employe.role}</td>
                                    <td className="border p-2">
                                        <span className={`px-2 py-1 rounded text-xs ${
                                            employe.disponible 
                                                ? 'bg-green-100 text-green-800' 
                                                : 'bg-red-100 text-red-800'
                                        }`}>
                                            {employe.disponible ? 'Disponible' : 'Affecté'}
                                        </span>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                )}
            </div>
        </div>
    );
}
