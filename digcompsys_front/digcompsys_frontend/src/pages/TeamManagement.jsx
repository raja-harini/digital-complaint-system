import { useState } from "react";
import API from "../services/api";

function TeamManagement() {
  const [teamName, setTeamName] = useState("");
  const [contact, setContact] = useState("");
  const [teamLeadId, setTeamLeadId] = useState("");
  const [employeeIds, setEmployeeIds] = useState("");

  const handleCreateTeam = async (e) => {
    e.preventDefault();

    try {
      const payload = {
        teamName,
        contact,
        teamLeadId: Number(teamLeadId),
        employeeIds: employeeIds.split(",").map(id => Number(id))
      };

      console.log("TEAM PAYLOAD:", payload);

      await API.post("/teams", payload);

      alert("Team created successfully");

    } catch (error) {
      console.log(error);
      alert("Error creating team");
    }
  };

  return (
    <div className="container mt-5">
      <h2>Team Management</h2>

      <form onSubmit={handleCreateTeam}>

        <input
          type="text"
          placeholder="Team Name"
          className="form-control mb-3"
          onChange={(e) => setTeamName(e.target.value)}
        />

        <input
          type="text"
          placeholder="Contact Number"
          className="form-control mb-3"
          onChange={(e) => setContact(e.target.value)}
        />

        <input
          type="number"
          placeholder="Team Lead ID"
          className="form-control mb-3"
          onChange={(e) => setTeamLeadId(e.target.value)}
        />

        <input
          type="text"
          placeholder="Employee IDs (comma separated)"
          className="form-control mb-3"
          onChange={(e) => setEmployeeIds(e.target.value)}
        />

        <button className="btn btn-success">Create Team</button>

      </form>
    </div>
  );
}

export default TeamManagement;