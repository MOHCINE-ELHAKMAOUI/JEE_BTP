export default function DevisCard({ projet }) {
    return (
        <div className="bg-secondary text-white rounded-xl shadow p-5 mb-6">
            <h3 className="text-xl font-bold mb-2">📄 Devis du projet</h3>

            <p><b>Type :</b> {projet.typeConstruction}</p>
            <p><b>Superficie :</b> {projet.superficieConstruite} m²</p>
            <p className="text-2xl font-bold mt-2">
                💰 {projet.coutTotal} DH
            </p>
        </div>
    );
}
