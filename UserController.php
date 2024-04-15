<?php
// src/Controller/UserController.php

namespace App\Controller;

use App\Entity\Utilisateur;
use App\Form\UtilisateurFormType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpKernel\Exception\NotFoundHttpException;

class UserController extends AbstractController
{
    #[Route('/users', name: 'user_index')]
    public function index(): Response
    {
        $utilisateurs = $this->getDoctrine()->getRepository(Utilisateur::class)->findAll();

        return $this->render('user/index.html.twig', [
            'utilisateurs' => $utilisateurs,
        ]);
    }

    #[Route('/signin', name: 'signin')] 
    public function new(Request $request): Response
    {
        $utilisateur = new Utilisateur();
        $form = $this->createForm(UtilisateurFormType::class, $utilisateur);

        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($utilisateur);
            $entityManager->flush();

            return $this->redirectToRoute('user_index');
        }

        return $this->render('user/new.html.twig', [
            'form' => $form->createView(),
        ]);
    }

    #[Route('/users/{id}', name: 'user_show')]
    public function show($id): Response
    {
        $utilisateur = $this->getDoctrine()->getRepository(Utilisateur::class)->find($id);

        if (!$utilisateur) {
            throw $this->createNotFoundException('Aucun utilisateur trouvé pour cet id ' . $id);
        }

        return $this->render('user/show.html.twig', [
            'utilisateur' => $utilisateur,
        ]);
    }

    #[Route('/modifier/{id}', name: 'modifier')]
    public function edit(Request $request, Utilisateur $utilisateur): Response
{
    $form = $this->createForm(UtilisateurFormType::class, $utilisateur);
    $form->handleRequest($request);

    if ($form->isSubmitted() && $form->isValid()) {
        // Gérer le champ mot de passe
        // Vérifier s'il y a un mot de passe soumis
        $submittedPassword = $form->get('motdepasse')->getData();
        if ($submittedPassword) {
            // Crypter le mot de passe
            $encodedPassword = $passwordEncoder->encodePassword($utilisateur, $submittedPassword);
            // Définir le mot de passe crypté sur l'utilisateur
            $utilisateur->setMotdepasse($encodedPassword);
        }

        // Gérer le champ photoD
        /** @var UploadedFile|null $uploadedFile */
        $uploadedFile = $form->get('photoD')->getData();
        if ($uploadedFile) {
            // Gérer le téléchargement de l'image (sauvegarde, traitement, etc.)
            // Enregistrez le chemin de l'image dans $utilisateur->setPhotoD()
        }

        // Enregistrer les modifications de l'utilisateur
        $this->getDoctrine()->getManager()->flush();

        // Rediriger vers la page de détails de l'utilisateur
        return $this->redirectToRoute('user_show', ['id' => $utilisateur->getId()]);
    }

    return $this->render('user/edit.html.twig', [
        'utilisateur' => $utilisateur,
        'form' => $form->createView(),
    ]);
}

    #[Route('/supprimer/{id}', name: 'delete')]
    public function delete(Request $request, Utilisateur $utilisateur): Response
    {
        if ($this->isCsrfTokenValid('delete' . $utilisateur->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($utilisateur);
            $entityManager->flush();
        }

        return $this->redirectToRoute('user_index');
    }
}
