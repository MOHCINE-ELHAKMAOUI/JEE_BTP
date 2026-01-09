export default function EmployeForm() {
    return (
        <div className="bg-white p-4 rounded-xl shadow">
            <h2 className="font-bold mb-3">👷 Ajouter un employé</h2>
            <input className="w-full border rounded p-2 mb-2" placeholder="Nom" />
            <select className="w-full border rounded p-2 mb-3">
                <option>OUVRIER</option>
                <option>INGENIEUR</option>
            </select>
            <button className="w-full bg-yellow-500 text-white py-2 rounded">Ajouter</button>
        </div>
    );
}
