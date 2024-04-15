<?php

namespace App\Entity;

use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;

/**
 * Transporteur
 *
 * @ORM\Table(name="transporteur", indexes={@ORM\Index(name="num_t", columns={"num_t"})})
 * @ORM\Entity
 */
class Transporteur
{
    /**
     * @var int
     *
     * @ORM\Column(name="num_ch", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $numCh;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=100, nullable=false)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom", type="string", length=100, nullable=false)
     */
    private $prenom;

    /**
     * @var int
     *
     * @ORM\Column(name="numtel", type="integer", nullable=false)
     */
    private $numtel;

    /**
     * @var string
     *
     * @ORM\Column(name="email", type="string", length=100, nullable=false)
     */
    private $email;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="daten", type="date", nullable=false)
     */
    private $daten;

    /**
     * @var int
     *
     * @ORM\Column(name="num_t", type="integer", nullable=false)
     */
    private $numT;

    public function getNumCh(): ?int
    {
        return $this->numCh;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): static
    {
        $this->nom = $nom;

        return $this;
    }

    public function getPrenom(): ?string
    {
        return $this->prenom;
    }

    public function setPrenom(string $prenom): static
    {
        $this->prenom = $prenom;

        return $this;
    }

    public function getNumtel(): ?int
    {
        return $this->numtel;
    }

    public function setNumtel(int $numtel): static
    {
        $this->numtel = $numtel;

        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): static
    {
        $this->email = $email;

        return $this;
    }

    public function getDaten(): ?\DateTimeInterface
    {
        return $this->daten;
    }

    public function setDaten(\DateTimeInterface $daten): static
    {
        $this->daten = $daten;

        return $this;
    }

    public function getNumT(): ?int
    {
        return $this->numT;
    }

    public function setNumT(int $numT): static
    {
        $this->numT = $numT;

        return $this;
    }


}
