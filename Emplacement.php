<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Emplacement
 *
 * @ORM\Table(name="emplacement")
 * @ORM\Entity
 */
class Emplacement
{
    /**
     * @var int
     *
     * @ORM\Column(name="emplacement_id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $emplacementId;

    /**
     * @var int|null
     *
     * @ORM\Column(name="category_id", type="integer", nullable=true)
     */
    private $categoryId;

    /**
     * @var int|null
     *
     * @ORM\Column(name="campsite_id", type="integer", nullable=true)
     */
    private $campsiteId;

    /**
     * @var string|null
     *
     * @ORM\Column(name="icon_path", type="string", length=255, nullable=true)
     */
    private $iconPath;

    public function getEmplacementId(): ?int
    {
        return $this->emplacementId;
    }

    public function getCategoryId(): ?int
    {
        return $this->categoryId;
    }

    public function setCategoryId(?int $categoryId): static
    {
        $this->categoryId = $categoryId;

        return $this;
    }

    public function getCampsiteId(): ?int
    {
        return $this->campsiteId;
    }

    public function setCampsiteId(?int $campsiteId): static
    {
        $this->campsiteId = $campsiteId;

        return $this;
    }

    public function getIconPath(): ?string
    {
        return $this->iconPath;
    }

    public function setIconPath(?string $iconPath): static
    {
        $this->iconPath = $iconPath;

        return $this;
    }


}
