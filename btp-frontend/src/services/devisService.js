import api from "../api/axios";

/**
 * Récupérer tous les devis
 */
export const getDevis = () => {
    return api.get("/devis");
};

/**
 * Récupérer le devis d’un projet précis
 */
export const getDevisByProjet = (projetId) => {
    return api.get(`/devis/projet/${projetId}`);
};
