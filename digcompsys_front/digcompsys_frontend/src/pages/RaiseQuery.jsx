import { useState } from "react";
import { useParams } from "react-router-dom";
import API from "../services/api";

function RaiseQuery() {
  const { id } = useParams();
  const [message, setMessage] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await API.post(`/complaints/${id}/query`, {
        message: message
      });

      alert("Query submitted");
      setMessage("");
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <div className="container mt-5">
      <h3>Raise Query</h3>

      <form onSubmit={handleSubmit}>
        <input
          type="text"
          className="form-control mt-3"
          placeholder="Enter your query..."
          value={message}
          onChange={(e) => setMessage(e.target.value)}
        />

        <button className="btn btn-danger mt-3">
          Submit Query
        </button>
      </form>
    </div>
  );
}

export default RaiseQuery;