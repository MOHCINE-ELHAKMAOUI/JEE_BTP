import { useEffect, useState } from "react";
import { getProjetsByEmploye } from "../services/employeService";
import { useNavigate } from "react-router-dom";

export default function EmployeeDashboard() {
    const [projets, setProjets] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");
    const [user, setUser] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        // Get user from localStorage
        const userData = localStorage.getItem("user");
        if (!userData) {
            navigate("/login");
            return;
        }

        const userObj = JSON.parse(userData);
        setUser(userObj);

        // Fetch projects for this employee
        fetchProjets(userObj.id);
    }, [navigate]);

    const fetchProjets = async (employeId) => {
        setLoading(true);
        try {
            const response = await getProjetsByEmploye(employeId);
            setProjets(response.data || []);
        } catch (err) {
            console.error("Error fetching projects:", err);
            setError("Erreur lors du chargement des projets");
        } finally {
            setLoading(false);
        }
    };

    if (loading) {
        return (
            <div className="min-h-screen bg-gray-100 p-6">
                <div className="max-w-7xl mx-auto">
                    <div className="bg-white shadow rounded-lg p-6">
                        <p className="text-center">Chargement de vos projets...</p>
                    </div>
                </div>
            </div>
        );
    }

    return (
        <div className="min-h-screen bg-gray-100 p-6">
            <div className="max-w-7xl mx-auto">
                <div className="bg-white shadow rounded-lg mb-6 p-4">
                    <div>
                        <h1 className="text-2xl font-bold text-gray-900">
                            Mes Projets
                        </h1>
                        {user && (
                            <p className="text-gray-600 mt-1">
                                {user.email} - {user.role}
                            </p>
                        )}
                    </div>
                </div>

                {error && (
                    <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4">
                        {error}
                    </div>
                )}

                {projets.length === 0 ? (
                    <div className="bg-white shadow rounded-lg p-8 text-center">
                        <p className="text-gray-500 text-lg">
                            Vous n'êtes actuellement affecté à aucun projet.
                        </p>
                        <p className="text-gray-400 text-sm mt-2">
                            Contactez votre administrateur pour être affecté à un projet.
                        </p>
                    </div>
                ) : (
                    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                        {projets.map((projet) => (
                            <div key={projet.id} className="bg-white shadow rounded-lg p-6">
                                <h3 className="text-xl font-bold text-gray-900 mb-2">
                                    Projet #{projet.id}
                                </h3>
                                <div className="space-y-2 text-sm">
                                    <p>
                                        <span className="font-semibold">Type:</span> {projet.typeConstruction}
                                    </p>
                                    <p>
                                        <span className="font-semibold">Superficie:</span> {projet.superficieConstruite} m²
                                    </p>
                                    <p>
                                        <span className="font-semibold">Statut:</span>{" "}
                                        <span className={`px-2 py-1 rounded text-xs ${
                                            projet.statut === 'EN_COURS' 
                                                ? 'bg-blue-100 text-blue-800' 
                                                : projet.statut === 'TERMINE'
                                                ? 'bg-green-100 text-green-800'
                                                : 'bg-gray-100 text-gray-800'
                                        }`}>
                                            {projet.statut}
                                        </span>
                                    </p>
                                    <p>
                                        <span className="font-semibold">Coût total:</span> {projet.coutTotal?.toFixed(2) || '0.00'} DH
                                    </p>
                                </div>
                            </div>
                        ))}
                    </div>
                )}
            </div>
        </div>
    );
}
