// export default function TerrainForm() {
//     return (
//         <div className="bg-white p-4 rounded-xl shadow">
//             <h2 className="font-bold mb-3">🏗️ Ajouter un terrain</h2>
//             <input className="w-full border rounded p-2 mb-2" placeholder="Localisation" />
//             <input type="number" className="w-full border rounded p-2 mb-3" placeholder="Superficie (m²)" />
//             <button className="w-full bg-blue-900 text-white py-2 rounded">Ajouter</button>
//         </div>
//     );
// }

import { useState } from "react";
import { ajouterTerrain } from "../services/terrainService";

export default function TerrainForm() {
    const [superficie, setSuperficie] = useState("");
    const [localisation, setLocalisation] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        await ajouterTerrain({ superficie, localisation });
        alert("Terrain ajouté !");
        setSuperficie("");
        setLocalisation("");
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2 className="font-bold">Ajouter Terrain</h2>
            <input value={localisation} onChange={e => setLocalisation(e.target.value)} placeholder="Localisation" />
            <input type="number" value={superficie} onChange={e => setSuperficie(e.target.value)} placeholder="Superficie" />
            <button>Enregistrer</button>
        </form>
    );
}
