export default function EmployeTable() {
    return (
        <div className="bg-white p-4 rounded-xl shadow">
            <h3 className="font-bold mb-3">👷 Employés</h3>
            <table className="w-full text-sm">
                <thead>
                <tr className="bg-gray-200">
                    <th className="p-2">Nom</th>
                    <th className="p-2">Rôle</th>
                    <th className="p-2">Statut</th>
                </tr>
                </thead>
                <tbody>
                <tr className="border-b">
                    <td className="p-2">Ahmed</td>
                    <td className="p-2">OUVRIER</td>
                    <td className="p-2 text-green-600">Disponible</td>
                </tr>
                </tbody>
            </table>
        </div>
    );
}
