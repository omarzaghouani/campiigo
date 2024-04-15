<?php

namespace App\Entity;

use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;

/**
 * Location
 *
 * @ORM\Table(name="location")
 * @ORM\Entity
 */
class Location
{
    /**
     * @var int
     *
     * @ORM\Column(name="idLoc", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idloc;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="dateEmprunt", type="date", nullable=false)
     */
    private $dateemprunt;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="dateFin", type="date", nullable=false)
     */
    private $datefin;

    /**
     * @var int
     *
     * @ORM\Column(name="cinUtil", type="integer", nullable=false)
     */
    private $cinutil;

    /**
     * @var string
     *
     * @ORM\Column(name="equip", type="string", length=255, nullable=false)
     */
    private $equip;

    /**
     * @var int
     *
     * @ORM\Column(name="nbrEquipements", type="integer", nullable=false)
     */
    private $nbrequipements;

    /**
     * @var int
     *
     * @ORM\Column(name="cout", type="integer", nullable=false)
     */
    private $cout;

    public function getIdloc(): ?int
    {
        return $this->idloc;
    }

    public function getDateemprunt(): ?\DateTimeInterface
    {
        return $this->dateemprunt;
    }

    public function setDateemprunt(\DateTimeInterface $dateemprunt): static
    {
        $this->dateemprunt = $dateemprunt;

        return $this;
    }

    public function getDatefin(): ?\DateTimeInterface
    {
        return $this->datefin;
    }

    public function setDatefin(\DateTimeInterface $datefin): static
    {
        $this->datefin = $datefin;

        return $this;
    }

    public function getCinutil(): ?int
    {
        return $this->cinutil;
    }

    public function setCinutil(int $cinutil): static
    {
        $this->cinutil = $cinutil;

        return $this;
    }

    public function getEquip(): ?string
    {
        return $this->equip;
    }

    public function setEquip(string $equip): static
    {
        $this->equip = $equip;

        return $this;
    }

    public function getNbrequipements(): ?int
    {
        return $this->nbrequipements;
    }

    public function setNbrequipements(int $nbrequipements): static
    {
        $this->nbrequipements = $nbrequipements;

        return $this;
    }

    public function getCout(): ?int
    {
        return $this->cout;
    }

    public function setCout(int $cout): static
    {
        $this->cout = $cout;

        return $this;
    }


}
