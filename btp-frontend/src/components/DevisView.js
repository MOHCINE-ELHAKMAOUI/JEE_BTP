// import { useEffect, useState } from "react";
// import axios from "axios";

// export default function DevisView({ projetId }) {
//     const [devis, setDevis] = useState(null);

//     useEffect(() => {
//         if (!projetId) return;

//         axios
//             .get(`http://localhost:8080/api/devis/projet/${projetId}`)
//             .then(res => setDevis(res.data))
//             .catch(err => console.error(err));
//     }, [projetId]);

//     if (!devis) return <p>Aucun devis</p>;

//     return (
//         <div className="bg-white p-6 rounded shadow">
//             <h2 className="text-xl font-bold mb-4">Devis du projet</h2>
//             <p><b>Superficie :</b> {devis.superficie} m²</p>
//             <p><b>Type :</b> {devis.typeConstruction}</p>
//             <p className="text-green-700 font-bold text-lg">
//                 Coût total : {devis.coutTotal} MAD
//             </p>
//         </div>
//     );
// }

import { useEffect, useState } from "react";
import { getDevis } from "../services/devisService";
import DevisCard from "./DevisCard";

export default function DevisViews() {
    const [devis, setDevis] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");

    useEffect(() => {
        getDevis()
            .then((res) => {
                setDevis(res.data);
                setLoading(false);
            })
            .catch((err) => {
                console.error(err);
                setError("Erreur lors du chargement des devis");
                setLoading(false);
            });
    }, []);

    if (loading) return <p>Chargement des devis...</p>;
    if (error) return <p className="text-red-600">{error}</p>;

    return (
        <div>
            <h2 className="text-xl font-bold mb-4">Devis des projets</h2>
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                {devis.map((d) => (
                    <DevisCard key={d.id} devis={d} />
                ))}
            </div>
        </div>
    );
}
