<?php

namespace App\Entity;

use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;

/**
 * Transport
 *
 * @ORM\Table(name="transport", indexes={@ORM\Index(name="num_ch", columns={"num_ch"}), @ORM\Index(name="num_v", columns={"num_v"})})
 * @ORM\Entity
 */
class Transport
{
    /**
     * @var int
     *
     * @ORM\Column(name="num_t", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $numT;

    /**
     * @var int
     *
     * @ORM\Column(name="num_ch", type="integer", nullable=false)
     */
    private $numCh;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="dd", type="date", nullable=false)
     */
    private $dd;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="da", type="date", nullable=false)
     */
    private $da;

    /**
     * @var int
     *
     * @ORM\Column(name="num_v", type="integer", nullable=false)
     */
    private $numV;

    /**
     * @var int
     *
     * @ORM\Column(name="cout", type="integer", nullable=false)
     */
    private $cout;

    public function getNumT(): ?int
    {
        return $this->numT;
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

    public function getDd(): ?\DateTimeInterface
    {
        return $this->dd;
    }

    public function setDd(\DateTimeInterface $dd): static
    {
        $this->dd = $dd;

        return $this;
    }

    public function getDa(): ?\DateTimeInterface
    {
        return $this->da;
    }

    public function setDa(\DateTimeInterface $da): static
    {
        $this->da = $da;

        return $this;
    }

    public function getNumV(): ?int
    {
        return $this->numV;
    }

    public function setNumV(int $numV): static
    {
        $this->numV = $numV;

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
