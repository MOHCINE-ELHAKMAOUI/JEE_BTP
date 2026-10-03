import MateriauList from "./MateriauList.jsx";
import DevisCard from "./DevisCard.jsx";

export default function Dashboard({ projetId }) {
    return (
        <div className="space-y-6">
            <MateriauList />
            <DevisCard projetId={projetId} />
        </div>
    );
}
