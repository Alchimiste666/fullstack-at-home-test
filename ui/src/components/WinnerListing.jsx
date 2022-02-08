const WinnerListing = ({ winners }) => {
  const mappedWinners = Object.entries(winners).map(([category, winner]) => (
    <div key={category} className="winner">
      <h5>{category}</h5>
      <p>{[winner.firstName, winner.middleName, winner.lastName].join(" ")}</p>
    </div>
  ));

  return (
    <div className="winner-listing">
      <h2 className="mb-5">Winners!</h2>
      {mappedWinners}
    </div>
  );
};

export default WinnerListing;
