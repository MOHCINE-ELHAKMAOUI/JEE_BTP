// import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
// import Dashboard from "./components/Dashboard";
// import ProjetForm from "./components/ProjetForm";
// import ProjetTable from "./components/ProjectTable";
// import EmployeForm from "./components/EmployeForm";
// import EmployeTable from "./components/EmployeTable";
// import MateriauList from "./components/MateriauList";
// import DevisView from "./components/DevisView";
// import Login from "./components/login";

// function App() {
//   return (
//     <Router>
//       <Routes>
//         <Route path="/login" element={<Login />} />
//         <Route path="/" element={<Dashboard />} />
//         <Route path="/projets" element={<ProjetTable />} />
//         <Route path="/projets/new" element={<ProjetForm />} />
//         <Route path="/employes" element={<EmployeTable />} />
//         <Route path="/employes/new" element={<EmployeForm />} />
//         <Route path="/materiaux" element={<MateriauList />} />
//         <Route path="/devis" element={<DevisView />} />
//       </Routes>
//     </Router>
//   );
// }

// export default App;


import React from "react";
import Dashboard from "./components/Dashboard";
import TerrainForm from "./components/TerrainForm";
import ProjetForm from "./components/ProjetForm";
import EmployeForm from "./components/EmployeForm";
import EmployeTable from "./components/EmployeTable";

// const [projetActif, setProjetActif] = useState(null);
export default function App() {
    return (
        <div className="min-h-screen bg-gray-100 p-6">
            <h1 className="text-3xl text-blue-900 mb-6">AGEP BTP</h1>

            <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
                <div className="space-y-6">

                    {/* <ProjetForm onProjetCreated={setProjetActif} />
                    <EmployeTable projetId={projetActif?.id} /> */}

                    {/* <TerrainForm /> */}
                    <ProjetForm />
                    <EmployeTable/>
                    {/* <EmployeForm /> */}
                </div>
                <div className="lg:col-span-2">
                    <Dashboard />
                </div>
            </div>
        </div>
    );
}
