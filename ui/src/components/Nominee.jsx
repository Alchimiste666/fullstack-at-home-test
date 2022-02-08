import classNames from "classnames";

const Nominee = ({ nominee, selectNominee, isSelected }) => {
  const { firstName, middleName, lastName, category } = nominee;

  return (
    <div className="col-xs-12 col-sm-6 col-md-4 col-lg-3 p-2">
      <div className={classNames("card nominee", { glow: isSelected })}>
        <div className="card-body text-center">
          <h6 className="card-title">
            {[firstName, middleName, lastName].join(" ")}
          </h6>
          <div className="p2">
            <img
              className="nominee-picture"
              src="https://st3.depositphotos.com/1432405/15064/v/600/depositphotos_150642962-stock-illustration-figure-in-a-hoodie-icon.jpg"
              alt="nominee picture"
              width={100}
            />
          </div>
          <div className="p2">
            <button
              type="button"
              className="btn btn-primary"
              onClick={() => selectNominee(category, nominee)}
            >
              Select
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Nominee;
