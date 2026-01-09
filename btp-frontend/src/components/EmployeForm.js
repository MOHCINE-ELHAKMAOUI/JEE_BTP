// export default function EmployeForm() {
//     return (
//         <div className="bg-white p-4 rounded-xl shadow">
//             <h2 className="font-bold mb-3">👷 Ajouter un employé</h2>
//             <input className="w-full border rounded p-2 mb-2" placeholder="Nom" />
//             <select className="w-full border rounded p-2 mb-3">
//                 <option>OUVRIER</option>
//                 <option>INGENIEUR</option>
//             </select>
//             <button className="w-full bg-yellow-500 text-white py-2 rounded">Ajouter</button>
//         </div>
//     );
// }

import { useState } from "react";
import { ajouterEmploye } from "../services/employeService";

export default function EmployeForm() {
    const [nom, setNom] = useState("");
    const [role, setRole] = useState("OUVRIER");

    const handleSubmit = async (e) => {
        e.preventDefault();
        await ajouterEmploye({ nom, role });
        alert("Employé ajouté !");
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2 className="font-bold">Ajouter Employé</h2>
            <input value={nom} onChange={e => setNom(e.target.value)} />
            <select onChange={e => setRole(e.target.value)}>
                <option>OUVRIER</option>
                <option>INGENIEUR</option>
            </select>
            <button>Ajouter</button>
        </form>
    );
}
