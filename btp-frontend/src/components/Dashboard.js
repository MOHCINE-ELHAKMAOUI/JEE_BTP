import MateriauList from "./MateriauList";
import DevisView from "./DevisView";

export default function Dashboard() {
    return (
        <div className="space-y-6">
            <MateriauList />
            {/* <DevisView projetId={3} /> ID EXISTANT */}
            <DevisView/>
        </div>
    );
}


// import { useState } from "react";
// import ProjetForm from "./ProjetForm";
// import ProjetTable from "./ProjectTable";
// import MateriauList from "./MateriauList";
// import DevisView from "./DevisView";
// import EmployeForm from "./EmployeForm";
// import EmployeTable from "./EmployeTable";

// function Dashboard() {
//   const [showProjetForm, setShowProjetForm] = useState(false);
//   const [showEmployeForm, setShowEmployeForm] = useState(false);

//   return (
//     <div style={{ padding: "20px" }}>
//       <h1>Dashboard BTP</h1>

//       {/* Section création projet */}
//       <section style={{ marginBottom: "40px", border: "1px solid #ccc", padding: "20px" }}>
//         <h2>Créer un projet</h2>
//         <button onClick={() => setShowProjetForm(!showProjetForm)}>
//           {showProjetForm ? "Masquer le formulaire" : "Ajouter un projet"}
//         </button>
//         {showProjetForm && <ProjetForm />}
//       </section>

//       {/* Section projets */}
//       <section style={{ marginBottom: "40px", border: "1px solid #ccc", padding: "20px" }}>
//         <h2>Liste des projets</h2>
//         <ProjetTable />
//       </section>

//       {/* Section matériaux */}
//       <section style={{ marginBottom: "40px", border: "1px solid #ccc", padding: "20px" }}>
//         <h2>Matériaux utilisés</h2>
//         <MateriauList />
//       </section>

//       {/* Section devis */}
//       <section style={{ marginBottom: "40px", border: "1px solid #ccc", padding: "20px" }}>
//         <h2>Devis</h2>
//         <DevisView />
//       </section>

//       {/* Section employés */}
//       <section style={{ marginBottom: "40px", border: "1px solid #ccc", padding: "20px" }}>
//         <h2>Gestion des employés</h2>
//         <button onClick={() => setShowEmployeForm(!showEmployeForm)}>
//           {showEmployeForm ? "Masquer le formulaire" : "Ajouter un employé"}
//         </button>
//         {showEmployeForm && <EmployeForm />}
//         <EmployeTable />
//       </section>
//     </div>
//   );
// }

// export default Dashboard;
