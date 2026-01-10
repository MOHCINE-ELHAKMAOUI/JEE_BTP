export default function MateriauTable({ materiaux }) {
    if (!materiaux || materiaux.length === 0) {
        return (
            <div>
                <h3 className="text-lg font-semibold mb-3">🧱 Matériaux utilisés</h3>
                <p className="text-gray-500 italic">Aucun matériau enregistré</p>
            </div>
        );
    }

    return (
        <div>
            <h3 className="text-lg font-semibold mb-3">🧱 Matériaux utilisés</h3>

            <div className="overflow-x-auto">
                <table className="w-full border text-sm">
                    <thead className="bg-gray-100">
                    <tr>
                        <th className="border p-2 text-left">Matériau</th>
                        <th className="border p-2 text-left">Quantité</th>
                        <th className="border p-2 text-left">Unité</th>
                        <th className="border p-2 text-left">Prix unitaire</th>
                        <th className="border p-2 text-right">Coût total</th>
                    </tr>
                    </thead>
                    <tbody>
                    {materiaux.map((pm) => (
                        <tr key={pm.id} className="border-b hover:bg-gray-50">
                            <td className="border p-2">{pm.materiau?.nom || 'N/A'}</td>
                            <td className="border p-2">{typeof pm.quantite === 'number' ? pm.quantite.toFixed(2) : pm.quantite}</td>
                            <td className="border p-2">{pm.materiau?.unite || 'N/A'}</td>
                            <td className="border p-2">{pm.materiau?.prixUnitaire ? `${pm.materiau.prixUnitaire.toFixed(2)} DH` : 'N/A'}</td>
                            <td className="border p-2 text-right font-semibold">
                                {typeof pm.cout === 'number' ? pm.cout.toFixed(2) : pm.cout} DH
                            </td>
                        </tr>
                    ))}
                    </tbody>
                    <tfoot>
                        <tr className="bg-gray-200 font-bold">
                            <td colSpan="4" className="border p-2 text-right">Total:</td>
                            <td className="border p-2 text-right">
                                {materiaux.reduce((sum, pm) => sum + (typeof pm.cout === 'number' ? pm.cout : 0), 0).toFixed(2)} DH
                            </td>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    );
}
