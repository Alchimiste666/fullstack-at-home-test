import "react-responsive-modal/styles.css";

import { useEffect, useState } from "react";
import { Modal } from "react-responsive-modal";

import { fetchArtists } from "../services/artist.service";
import NomineeListing from "./NomineeListing";
import FloatingButton from "./FloatingButton";
import WinnerListing from "./WinnerListing";

const Ballot = () => {
  // Nominees list
  const [nominees, setNominees] = useState([]);

  // Selected nominees
  const [selectedNominees, setSelectedNominees] = useState({});

  // select nominee (category / ID of nominee)
  const selectNominee = (category, nominee) => {
    setSelectedNominees({ ...selectedNominees, [category]: nominee });
  };

  // Modal display
  const [isModalOpen, showModal] = useState(false);

  const onOpenModal = () => showModal(true);
  const onCloseModal = () => showModal(false);

  useEffect(() => {
    async function loadArtists() {
      const artists = await fetchArtists();
      setNominees(artists);
    }
    loadArtists();
  }, []);

  return (
    <div>
      <NomineeListing
        nominees={nominees}
        selectedNominees={selectedNominees}
        selectNominee={selectNominee}
      />
      <FloatingButton label="Submit ballot" action={onOpenModal} />
      <Modal open={isModalOpen} onClose={onCloseModal} center>
        <h2>Winners!</h2>
        <WinnerListing winners={selectedNominees} />
      </Modal>
    </div>
  );
};

export default Ballot;
