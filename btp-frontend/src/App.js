import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Dashboard from "./components/Dashboard";
import ProjetForm from "./components/ProjetForm";
import EmployeTable from "./components/EmployeTable";
import LoginForm from "./components/LoginForm";
import RegisterForm from "./components/RegisterForm";
import EmployeeDashboard from "./components/EmployeeDashboard";
import Navbar from "./components/Navbar";
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
    
    return (
        <>
            <Navbar />
            <div className="min-h-screen bg-gray-100 p-6">
                <h1 className="text-3xl text-blue-900 mb-6">Tableau de Bord - Administration</h1>

                <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
                    <div className="space-y-6">
                        <ProjetForm onProjetCreated={setProjetActif} />
                        <EmployeTable projetId={projetActif?.id} />
                    </div>
                    <div className="lg:col-span-2">
                        <Dashboard projetId={projetActif?.id} />
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
