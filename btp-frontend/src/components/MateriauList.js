import { useEffect, useState } from "react";
import axios from "axios";

export default function MateriauList() {
    const [materiaux, setMateriaux] = useState([]);

    useEffect(() => {
        axios
            .get("http://localhost:8080/api/materiaux")
            .then(res => setMateriaux(res.data))
            .catch(err => console.error(err));
    }, []);

    return (
        <div className="bg-white p-6 rounded shadow">
            <h2 className="text-xl font-bold mb-4">Matériaux</h2>

            <table className="w-full border">
                <thead className="bg-gray-100">
                <tr>
                    <th className="border p-2">Nom</th>
                    <th className="border p-2">Prix</th>
                </tr>
                </thead>
                <tbody>
                {materiaux.map(m => (
                    <tr key={m.id}>
                        <td className="border p-2">{m.nom}</td>
                        <td className="border p-2">{m.prixUnitaire}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}
