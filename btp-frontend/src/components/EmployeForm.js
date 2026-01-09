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
