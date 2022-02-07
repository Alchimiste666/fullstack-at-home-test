const api = {
  getBallotData() {
    return fetch('http://localhost:8080/artists').then(res => {
      return res.json();
    });
  }
};

export default api;