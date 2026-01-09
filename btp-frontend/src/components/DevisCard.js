// export default function DevisCard({ projet }) {
//     return (
//         <div className="bg-secondary text-white rounded-xl shadow p-5 mb-6">
//             <h3 className="text-xl font-bold mb-2">📄 Devis du projet</h3>

//             <p><b>Type :</b> {projet.typeConstruction}</p>
//             <p><b>Superficie :</b> {projet.superficieConstruite} m²</p>
//             <p className="text-2xl font-bold mt-2">
//                 💰 {projet.coutTotal} DH
//             </p>
//         </div>
//     );
// }

export default function DevisCard({ devis }) {
    return (
        <div className="border p-4 rounded shadow bg-white">
            <h3 className="font-bold text-lg">
                Projet #{devis.projet?.id}
            </h3>

            <p>Type : {devis.projet?.typeConstruction}</p>
            <p>Superficie : {devis.projet?.superficieConstruite} m²</p>
            <p className="font-semibold mt-2">
                Coût total : {devis.coutTotal} DH
            </p>
        </div>
    );
}
