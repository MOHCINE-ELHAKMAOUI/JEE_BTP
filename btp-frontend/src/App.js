import React from "react";
import Dashboard from "./components/Dashboard";
import TerrainForm from "./components/TerrainForm";
import ProjectForm from "./components/ProjectForm";
import EmployeForm from "./components/EmployeForm";

export default function App() {
    return (
        <div className="min-h-screen bg-gray-100 p-6">
            <h1 className="text-3xl text-blue-900 mb-6">AGEP BTP</h1>

            <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
                <div className="space-y-6">
                    <TerrainForm />
                    <ProjectForm />
                    <EmployeForm />
                </div>
                <div className="lg:col-span-2">
                    <Dashboard />
                </div>
            </div>
        </div>
    );
}
