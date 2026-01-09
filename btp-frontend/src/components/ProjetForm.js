// // import { useState } from "react";
// // import { creerProjet } from "../services/projetService";

// // export default function ProjetForm() {
// //     const [superficie, setSuperficie] = useState("");
// //     const [typeConstruction, setTypeConstruction] = useState("MAISON");

// //     const handleSubmit = async (e) => {
// //         e.preventDefault();
// //         await creerProjet({ superficie, typeConstruction });
// //         alert("Projet créé !");
// //     };

// //     return (
// //         <form onSubmit={handleSubmit}>
// //             <h2 className="font-bold">Créer Projet</h2>
// //             <input type="number" value={superficie} onChange={e => setSuperficie(e.target.value)} />
// //             <select onChange={e => setTypeConstruction(e.target.value)}>
// //                 <option>MAISON</option>
// //                 <option>VILLA</option>
// //                 <option>R+1</option>
// //             </select>
// //             <button>Créer</button>
// //         </form>
// //     );
// // }

// import { creerProjet } from "../services/projetService";
// import { useState } from "react";

// const handleSubmit = async (e) => {
//     e.preventDefault();

//     const res = await creerProjet({
//         superficieConstruite: superficie,
//         typeConstruction
//     });

//     // 🔥 Stocker l’ID du projet créé
//     localStorage.setItem("projetActifId", res.data.id);

//     alert("Projet créé ! Vous pouvez affecter des employés.");
// };

// function ProjetForm() {
//     const [superficie, setSuperficie] = useState("");
//     const [typeConstruction, setTypeConstruction] = useState("MAISON");

//     const handleSubmit = (e) => {
//         e.preventDefault();
//         console.log(superficie, typeConstruction);
//     };

//     return (
//         <form>
//             <h2>Créer Projet</h2>
//             <input
//                 type="number"
//                 value={superficie}
//                 onChange={(e) => setSuperficie(e.target.value)}
//             />
//             <select onChange={(e) => setTypeConstruction(e.target.value)}>
//                 <option>MAISON</option>
//                 <option>VILLA</option>
//                 <option>R+1</option>
//             </select>
//             <button>Créer</button>
//         </form>
//     );
// }

// export default ProjetForm;

import { useState } from "react";
import { createProjet } from "../services/projetService";

function ProjetForm({ onProjetCreated }) {
    const [typeConstruction, setTypeConstruction] = useState("MAISON");
    const [superficieConstruite, setSuperficieConstruite] = useState("");
    const [statut, setStatut] = useState("EN_COURS");

    const handleSubmit = (e) => {
        e.preventDefault();

        const nouveauProjet = {
            typeConstruction,
            superficieConstruite: Number(superficieConstruite),
            statut,
            coutTotal:0
        };

        createProjet(nouveauProjet)
            .then((res) => {
                alert("Projet créé avec succès");
                if (onProjetCreated) {
                    onProjetCreated(res.data); // pour l'affectation employés
                }
                setSuperficieConstruite("");
            })
            .catch((err) => {
                console.error(err);
                alert("Erreur lors de la création du projet");
            });
    };

    return (
        <div className="bg-white p-4 rounded shadow">
            <h2 className="text-xl font-bold mb-4">Créer un projet</h2>

            <form onSubmit={handleSubmit} className="space-y-4">
                {/* Type de construction */}
                <div>
                    <label className="block font-medium">Type de construction</label>
                    <select
                        className="w-full border p-2 rounded"
                        value={typeConstruction}
                        onChange={(e) => setTypeConstruction(e.target.value)}
                    >
                        <option value="MAISON">Maison</option>
                        <option value="VILLA">Villa</option>
                        <option value="R_PLUS_1">R+1</option>
                        <option value="IMMEUBLE">Immeuble</option>
                    </select>
                </div>

                {/* Superficie */}
                <div>
                    <label className="block font-medium">Superficie construite (m²)</label>
                    <input
                        type="number"
                        className="w-full border p-2 rounded"
                        value={superficieConstruite}
                        onChange={(e) => setSuperficieConstruite(e.target.value)}
                        required
                    />
                </div>

                {/* Statut */}
                <div>
                    <label className="block font-medium">Statut</label>
                    <select
                        className="w-full border p-2 rounded"
                        value={statut}
                        onChange={(e) => setStatut(e.target.value)}
                    >
                        <option value="EN_COURS">En cours</option>
                        <option value="TERMINE">Terminé</option>
                        <option value="PLANIFIE">Planifié</option>
                    </select>
                </div>

                <button
                    type="submit"
                    className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
                >
                    Créer le projet
                </button>
            </form>
        </div>
    );
}

export default ProjetForm;
