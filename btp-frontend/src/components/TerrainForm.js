export default function TerrainForm() {
    return (
        <div className="bg-white p-4 rounded-xl shadow">
            <h2 className="font-bold mb-3">🏗️ Ajouter un terrain</h2>
            <input className="w-full border rounded p-2 mb-2" placeholder="Localisation" />
            <input type="number" className="w-full border rounded p-2 mb-3" placeholder="Superficie (m²)" />
            <button className="w-full bg-blue-900 text-white py-2 rounded">Ajouter</button>
        </div>
    );
}
