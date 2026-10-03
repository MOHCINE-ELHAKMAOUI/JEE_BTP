import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Dashboard from "./components/Dashboard.jsx";
import ProjetForm from "./components/ProjetForm.jsx";
import EmployeTable from "./components/EmployeTable.jsx";
import LoginForm from "./components/LoginForm.jsx";
import RegisterForm from "./components/RegisterForm.jsx";
import EmployeeDashboard from "./components/EmployeeDashboard.jsx";
import Navbar from "./components/Navbar.jsx";
import DevisSearch from "./components/DevisSearch.jsx";
import React, { useState } from "react";

// Private Route Component for employee dashboard
function EmployeeRoute({ children }) {
    const user = localStorage.getItem("user");
    if (!user) {
        return (
            <>
                <Navbar />
                <div className="min-h-screen bg-gray-100 flex items-center justify-center">
                    <div className="text-center">
                        <p className="text-gray-600 mb-4">Vous devez être connecté pour accéder à cette page</p>
                        <a href="/login" className="text-blue-600 hover:text-blue-800">Se connecter</a>
                    </div>
                </div>
            </>
        );
    }
    return children;
}

function MainDashboard() {
    const [projetActif, setProjetActif] = useState(null);
    const [projetActifFromDevis, setProjetActifFromDevis] = useState(null);
    
    const handleDevisFound = (projetId) => {
        if (projetId) {
            // Set the project ID from devis search
            setProjetActifFromDevis({ id: projetId });
            // Also update projetActif if it's from devis search
            setProjetActif({ id: projetId });
        } else {
            // Clear if devis search is cleared
            setProjetActifFromDevis(null);
            if (!projetActif) {
                setProjetActif(null);
            }
        }
    };
    
    // Use projetActifFromDevis if set, otherwise use projetActif from form
    const activeProjetId = projetActifFromDevis?.id || projetActif?.id;
    
    return (
        <>
            <Navbar />
            <div className="min-h-screen bg-green-100/30 p-6">
                <h1 className="text-3xl text-green-500 mb-6">Tableau de Bord - Administration</h1>

                <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
                    <div className="space-y-6">
                        <DevisSearch onDevisFound={handleDevisFound} />
                        <ProjetForm onProjetCreated={(projet) => {
                            setProjetActif(projet);
                            // Clear devis search project if creating a new project
                            setProjetActifFromDevis(null);
                        }} />
                        <EmployeTable projetId={activeProjetId} />
                    </div>
                    <div className="lg:col-span-2">
                        <Dashboard projetId={activeProjetId} />
                    </div>
                </div>
            </div>
        </>
    );
}

function App() {
    return (
        <Router>
            <Routes>
                {/* Main dashboard - default page */}
                <Route path="/" element={<MainDashboard />} />
                
                {/* Public routes */}
                <Route path="/login" element={
                    <>
                        <Navbar />
                        <LoginForm />
                    </>
                } />
                <Route path="/register" element={
                    <>
                        <Navbar />
                        <RegisterForm />
                    </>
                } />
                
                {/* Protected routes */}
                <Route path="/employee/dashboard" element={
                    <>
                        <Navbar />
                        <EmployeeRoute>
                            <EmployeeDashboard />
                        </EmployeeRoute>
                    </>
                } />
            </Routes>
        </Router>
    );
}

export default App;
