import MateriauList from "./MateriauList";
import DevisCard from "./DevisCard";

export default function Dashboard({ projetId }) {
    return (
        <div className="space-y-6">
            <MateriauList />
            <DevisCard projetId={projetId} />
        </div>
    );
}
