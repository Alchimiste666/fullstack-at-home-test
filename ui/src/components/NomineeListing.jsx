import { groupBy, lowerCase, capitalize, isEmpty } from "lodash-es";

import Nominee from "./Nominee";

const NomineeListing = ({ nominees, selectedNominees, selectNominee }) => {
  const nomineeGroups = groupBy(nominees, "category");

  const mappedNomineeGroups = Object.entries(nomineeGroups).map(
    ([category, groupNominees]) => {
      const mappedGroupNominees = groupNominees.map((nominee) => {
        const selectedCategoryNominee = selectedNominees[nominee.category];
        const isSelected =
          !isEmpty(selectedCategoryNominee) &&
          selectedCategoryNominee.id === nominee.id;

        return (
          <Nominee
            key={nominee.id}
            nominee={nominee}
            selectNominee={selectNominee}
            isSelected={isSelected}
          />
        );
      });

      return (
        <div key={category} className="category">
          <h4 className="category-title">{capitalize(lowerCase(category))}</h4>
          <div className="nominees-group row mb-5">{mappedGroupNominees}</div>
        </div>
      );
    }
  );

  return <div className="nominees">{mappedNomineeGroups}</div>;
};

export default NomineeListing;
