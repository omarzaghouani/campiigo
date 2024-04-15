<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Vehicule
 *
 * @ORM\Table(name="vehicule", indexes={@ORM\Index(name="num_ch", columns={"num_ch"})})
 * @ORM\Entity
 */
class Vehicule
{
    /**
     * @var int
     *
     * @ORM\Column(name="num_v", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $numV;

    /**
     * @var string
     *
     * @ORM\Column(name="type", type="string", length=100, nullable=false)
     */
    private $type;

    /**
     * @var int
     *
     * @ORM\Column(name="capacite", type="integer", nullable=false)
     */
    private $capacite;

    /**
     * @var int
     *
     * @ORM\Column(name="prixuni", type="integer", nullable=false)
     */
    private $prixuni;

    /**
     * @var int
     *
     * @ORM\Column(name="num_ch", type="integer", nullable=false)
     */
    private $numCh;

    public function getNumV(): ?int
    {
        return $this->numV;
    }

    public function getType(): ?string
    {
        return $this->type;
    }

    public function setType(string $type): static
    {
        $this->type = $type;

        return $this;
    }

    public function getCapacite(): ?int
    {
        return $this->capacite;
    }

    public function setCapacite(int $capacite): static
    {
        $this->capacite = $capacite;

        return $this;
    }

    public function getPrixuni(): ?int
    {
        return $this->prixuni;
    }

    public function setPrixuni(int $prixuni): static
    {
        $this->prixuni = $prixuni;

        return $this;
    }

    public function getNumCh(): ?int
    {
        return $this->numCh;
    }

    public function setNumCh(int $numCh): static
    {
        $this->numCh = $numCh;

        return $this;
    }


}
