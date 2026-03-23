import { useParams } from "react-router-dom";
import { useState } from "react";
import API from "../services/api";

function ComplaintAssignment() {
  const { id } = useParams();
  const [teamId, setTeamId] = useState("");

  const handleAssign = async (e) => {
    e.preventDefault();

    try {
      await API.post(`/complaints/${id}/assign`, {
        teamId: Number(teamId),
      });

      alert("Assigned successfully");

    } catch (error) {
      console.log(error);
      alert("Assignment failed");
    }
  };

  return (
    <div className="container mt-5">
      <h2>Assign Complaint ID: {id}</h2>

      <form onSubmit={handleAssign}>
        <input
          type="number"
          placeholder="Team ID"
          className="form-control mb-3"
          onChange={(e) => setTeamId(e.target.value)}
        />

        <button className="btn btn-success">Assign</button>
      </form>
    </div>
  );
}

export default ComplaintAssignment;