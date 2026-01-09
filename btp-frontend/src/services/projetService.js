// import api from "../api/axios";

// export const getProjets = () => {
//   return api.get("/projets");
// };

// export const creerProjet = (terrainId, typeConstruction, superficie) => {
//   return api.post(
//     `/projets?terrainId=${terrainId}&typeConstruction=${typeConstruction}&superficie=${superficie}`
//   );
// };

import api from "../api/axios";

// export const creerProjet = (projet) => {
//     return api.post("/projets", projet);
// };

export const createProjet = (projet) => {
    return api.post("/projets", projet);
};

export const getProjets = () => {
    return api.get("/projets");
};

export const affecterEmploye = (projetId, employeId) => {
    return api.post(`/projets/${projetId}/employes/${employeId}`);
};


// export const getProjetId = (id) => {
//     return api.get("/projets/{id}");
// };
