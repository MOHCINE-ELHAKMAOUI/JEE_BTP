import { useState } from "react";
import { register } from "../services/authService";
import { useNavigate, Link } from "react-router-dom";

export default function RegisterForm() {
    const [formData, setFormData] = useState({
        nom: "",
        email: "",
        motDePasse: "",
        role: "OUVRIER"
    });
    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError("");
        setLoading(true);

        try {
            const response = await register(formData);
            
            // Store JWT token
            if (response.data.token) {
                localStorage.setItem("token", response.data.token);
            }
            
            // Store user info in localStorage
            localStorage.setItem("user", JSON.stringify({
                id: response.data.id,
                email: response.data.email,
                role: response.data.role
            }));
            
            alert("Inscription réussie ! Redirection vers votre tableau de bord...");
            navigate("/employee/dashboard");
        } catch (err) {
            console.error("Registration error:", err);
            const errorMessage = err.response?.data?.message 
                || err.response?.statusText 
                || err.message 
                || "Erreur lors de l'inscription";
            setError(errorMessage);
        } finally {
            setLoading(false);
        }
    };

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    return (
        <div className="min-h-screen bg-gray-100 flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8">
            <div className="max-w-md w-full space-y-8 bg-white p-8 rounded-lg shadow-md">
                <div>
                    <h2 className="mt-6 text-center text-3xl font-extrabold text-gray-900">
                        Créer un compte employé
                    </h2>
                    <p className="mt-2 text-center text-sm text-gray-600">
                        Ou{" "}
                        <Link to="/login" className="font-medium text-blue-600 hover:text-blue-500">
                            connectez-vous à votre compte existant
                        </Link>
                    </p>
                    <div className="mt-2 text-center">
                        <Link to="/" className="text-sm text-gray-500 hover:text-gray-700">
                            ← Retour à l'accueil
                        </Link>
                    </div>
                </div>
                
                <form className="mt-8 space-y-6" onSubmit={handleSubmit}>
                    {error && (
                        <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded">
                            {error}
                        </div>
                    )}

                    <div className="space-y-4">
                        <div>
                            <label htmlFor="nom" className="block text-sm font-medium text-gray-700">
                                Nom complet
                            </label>
                            <input
                                id="nom"
                                name="nom"
                                type="text"
                                required
                                className="mt-1 appearance-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-md focus:outline-none focus:ring-blue-500 focus:border-blue-500 focus:z-10 sm:text-sm"
                                placeholder="Votre nom complet"
                                value={formData.nom}
                                onChange={handleChange}
                            />
                        </div>

                        <div>
                            <label htmlFor="email" className="block text-sm font-medium text-gray-700">
                                Email
                            </label>
                            <input
                                id="email"
                                name="email"
                                type="email"
                                required
                                className="mt-1 appearance-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-md focus:outline-none focus:ring-blue-500 focus:border-blue-500 focus:z-10 sm:text-sm"
                                placeholder="votre.email@example.com"
                                value={formData.email}
                                onChange={handleChange}
                            />
                        </div>

                        <div>
                            <label htmlFor="motDePasse" className="block text-sm font-medium text-gray-700">
                                Mot de passe
                            </label>
                            <input
                                id="motDePasse"
                                name="motDePasse"
                                type="password"
                                required
                                minLength={6}
                                className="mt-1 appearance-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-md focus:outline-none focus:ring-blue-500 focus:border-blue-500 focus:z-10 sm:text-sm"
                                placeholder="Minimum 6 caractères"
                                value={formData.motDePasse}
                                onChange={handleChange}
                            />
                        </div>

                        <div>
                            <label htmlFor="role" className="block text-sm font-medium text-gray-700">
                                Rôle
                            </label>
                            <select
                                id="role"
                                name="role"
                                required
                                className="mt-1 block w-full px-3 py-2 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                                value={formData.role}
                                onChange={handleChange}
                            >
                                <option value="OUVRIER">Ouvrier</option>
                                <option value="INGENIEUR">Ingénieur</option>
                            </select>
                        </div>
                    </div>

                    <div>
                        <button
                            type="submit"
                            disabled={loading}
                            className={`group relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white ${
                                loading 
                                    ? 'bg-gray-400 cursor-not-allowed' 
                                    : 'bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500'
                            }`}
                        >
                            {loading ? "Inscription en cours..." : "S'inscrire"}
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}
