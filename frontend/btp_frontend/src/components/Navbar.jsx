import { Link, useNavigate } from "react-router-dom";

export default function Navbar() {
    const navigate = useNavigate();
    const user = JSON.parse(localStorage.getItem("user") || "null");

    const handleLogout = () => {
        localStorage.removeItem("user");
        navigate("/");
    };

    return (
        <nav className="bg-green-400/20 text-white shadow-lg">
            <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                <div className="flex justify-between items-center h-16">
                    <div className="flex items-center">
                        <Link to="/" className="text-xl font-bold text-green-600">
                            AGEP BTP
                        </Link>
                    </div>
                    
                    <div className="flex items-center space-x-4">
                        <Link 
                            to="/" 
                            className="px-3 py-2 rounded-md text-sm font-medium bg-green-600 hover:bg-green-700 transition"
                        >
                            Accueil
                        </Link>
                        
                        {user ? (
                            <>
                                <Link
                                    to="/employee/dashboard"
                                    className="px-3 py-2 rounded-md text-sm font-medium bg-green-600 hover:bg-green-700 transition"
                                >
                                    Mes Projets
                                </Link>
                                <button
                                    onClick={handleLogout}
                                    className="px-3 py-2 rounded-md text-sm font-medium bg-red-600 hover:bg-red-700 transition"
                                >
                                    Déconnexion
                                </button>
                            </>
                        ) : (
                            <Link
                                to="/login"
                                className="px-3 py-2 rounded-md text-sm font-medium bg-green-600 hover:bg-green-700 transition"
                            >
                                Connexion
                            </Link>
                        )}
                    </div>
                </div>
            </div>
        </nav>
    );
}
