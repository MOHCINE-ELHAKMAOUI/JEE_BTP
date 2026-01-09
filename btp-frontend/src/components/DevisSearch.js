import { useState } from "react";
import { getDevisById } from "../services/devisService";

export default function DevisSearch({ onDevisFound }) {
    const [devisId, setDevisId] = useState("");
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");
    const [devis, setDevis] = useState(null);

    const handleSearch = async (e) => {
        e.preventDefault();
        
        if (!devisId || isNaN(devisId)) {
            setError("Veuillez entrer un ID de devis valide");
            return;
        }

        setLoading(true);
        setError("");
        setDevis(null);

        try {
            const response = await getDevisById(devisId);
            const foundDevis = response.data;
            setDevis(foundDevis);
            
            // Call the callback with the project ID from the devis
            if (foundDevis.projet && onDevisFound) {
                onDevisFound(foundDevis.projet.id);
            }
        } catch (err) {
            console.error("Error searching devis:", err);
            const errorMessage = err.response?.data?.message 
                || err.response?.statusText 
                || err.message 
                || "Devis non trouvé";
            setError(errorMessage);
            setDevis(null);
        } finally {
            setLoading(false);
        }
    };

    const handleClear = () => {
        setDevisId("");
        setDevis(null);
        setError("");
        if (onDevisFound) {
            onDevisFound(null);
        }
    };

    return (
        <div className="bg-white p-4 rounded shadow">
            <h2 className="text-xl font-bold mb-4">Rechercher un devis</h2>
            
            <form onSubmit={handleSearch} className="space-y-4">
                <div className="flex gap-2">
                    <input
                        type="number"
                        className="flex-1 border p-2 rounded"
                        placeholder="ID du devis"
                        value={devisId}
                        onChange={(e) => setDevisId(e.target.value)}
                        min="1"
                        required
                    />
                    <button
                        type="submit"
                        disabled={loading}
                        className={`px-4 py-2 rounded text-white ${
                            loading 
                                ? 'bg-gray-400 cursor-not-allowed' 
                                : 'bg-blue-600 hover:bg-blue-700'
                        }`}
                    >
                        {loading ? "Recherche..." : "Rechercher"}
                    </button>
                    {devis && (
                        <button
                            type="button"
                            onClick={handleClear}
                            className="px-4 py-2 rounded bg-gray-500 text-white hover:bg-gray-600"
                        >
                            Effacer
                        </button>
                    )}
                </div>
            </form>

            {error && (
                <div className="mt-4 bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded">
                    {error}
                </div>
            )}

            {devis && (
                <div className="mt-4 bg-green-50 border border-green-200 rounded p-4">
                    <h3 className="font-semibold text-green-800 mb-2">✓ Devis trouvé</h3>
                    <div className="space-y-2 text-sm">
                        <p>
                            <span className="font-semibold">ID Devis:</span> {devis.id}
                        </p>
                        <p>
                            <span className="font-semibold">Projet ID:</span> {devis.projet?.id || "N/A"}
                        </p>
                        <p>
                            <span className="font-semibold">Montant total:</span> {devis.montantTotal?.toFixed(2) || "0.00"} DH
                        </p>
                        <p>
                            <span className="font-semibold">Date:</span> {devis.dateCreation ? (typeof devis.dateCreation === 'string' ? new Date(devis.dateCreation).toLocaleDateString() : devis.dateCreation) : 'N/A'}
                        </p>
                        {devis.projet && (
                            <p className="text-green-700 font-medium mt-2">
                                Vous pouvez maintenant affecter des employés au projet #{devis.projet.id}
                            </p>
                        )}
                    </div>
                </div>
            )}
        </div>
    );
}
