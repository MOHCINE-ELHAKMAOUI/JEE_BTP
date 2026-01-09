export default function MateriauTable({ materiaux }) {
    return (
        <div className="bg-white rounded-xl shadow p-4">
            <h3 className="font-bold mb-3">🧱 Matériaux</h3>

            <table className="w-full text-sm">
                <thead>
                <tr className="bg-gray-100">
                    <th className="p-2">Matériau</th>
                    <th className="p-2">Quantité</th>
                    <th className="p-2">Coût</th>
                </tr>
                </thead>
                <tbody>
                {materiaux.map((m, i) => (
                    <tr key={i} className="border-b">
                        <td className="p-2">{m.nomMateriau}</td>
                        <td className="p-2">{m.quantite}</td>
                        <td className="p-2 font-semibold">{m.cout} DH</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}
