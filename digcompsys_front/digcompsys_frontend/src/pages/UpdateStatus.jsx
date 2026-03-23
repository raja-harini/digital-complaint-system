import { useParams } from "react-router-dom";
import { useState } from "react";
import API from "../services/api";

function UpdateStatus() {
  const { id } = useParams();
  const [status, setStatus] = useState("IN_PROGRESS");

  const handleUpdate = async (e) => {
    e.preventDefault();

    try {
      await API.put(`/complaints/${id}/status`, {
        newStatus: status
      });

      alert("Status updated");

    } catch (error) {
      console.log(error);
      alert("Update failed");
    }
  };

  return (
    <div className="container mt-5">
      <h2>Update Complaint Status</h2>

      <form onSubmit={handleUpdate}>

        <select
          className="form-control mb-3"
          value={status}
          onChange={(e) => setStatus(e.target.value)}
        >
          <option value="IN_PROGRESS">In Progress</option>
          <option value="RESOLVED">Resolved</option>
          <option value="REJECTED">Rejected</option>
        </select>

        <button className="btn btn-success">Update</button>

      </form>
    </div>
  );
}

export default UpdateStatus;