export default function ProjetForm() {
    return (
        <div className="bg-white p-4 rounded-xl shadow">
            <h2 className="font-bold mb-3">🏠 Créer un projet</h2>
            <select className="w-full border rounded p-2 mb-2">
                <option>MAISON</option>
                <option>VILLA</option>
                <option>R+1</option>
            </select>
            <input type="number" className="w-full border rounded p-2 mb-3" placeholder="Superficie construite" />
            <button className="w-full bg-green-600 text-white py-2 rounded">Calculer devis</button>
        </div>
    );
}
