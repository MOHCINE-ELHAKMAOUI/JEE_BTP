import { useEffect, useState } from "react";
import { getMateriaux } from "../services/materiauService";

export default function MateriauList() {
    const [materiaux, setMateriaux] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");

    useEffect(() => {
        getMateriaux()
            .then((res) => {
                setMateriaux(res.data);
                setLoading(false);
            })
            .catch((err) => {
                console.error(err);
                setError("Erreur lors du chargement des matériaux");
                setLoading(false);
            });
    }, []);

    if (loading) return <p>Chargement des matériaux...</p>;
    if (error) return <p className="text-red-600">{error}</p>;

    return (
        <div className="bg-white p-4 rounded shadow">
            <h2 className="text-xl font-bold mb-4">Liste des prix matériaux</h2>

            <table className="w-full border">
                <thead className="bg-green-100">
                    <tr>
                        <th className="border p-2">Nom</th>
                        <th className="border p-2">Prix unitaire</th>
                        <th className="border p-2">Unité</th>
                    </tr>
                </thead>
                <tbody>
                    {materiaux.map((m) => (
                        <tr key={m.id}>
                            <td className="border p-2">{m.nom}</td>
                            <td className="border p-2">{m.prixUnitaire} DH</td>
                            <td className="border p-2">{m.unite}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}
