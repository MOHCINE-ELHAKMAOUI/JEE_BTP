import api from "../api/axios";


export const createProjet = (projet) => {
    return api.post("/projets", projet);
};

export const getProjets = () => {
    return api.get("/projets");
};

export const affecterEmploye = (projetId, employeId) => {
    return api.post(`/projets/${projetId}/employes/${employeId}`);
};
