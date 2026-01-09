import api from "../api/axios";


export const getDevis = () => {
    return api.get("/devis");
};

export const genererDevis = (projetId) => {
    return api.post(`/devis/projet/${projetId}`);
};

export const getDevisByProjet = (projetId) => {
    return api.get(`/devis/projet/${projetId}`);
};
