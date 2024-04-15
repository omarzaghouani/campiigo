<?php

// src/Controller/HomeController.php

namespace App\Controller;
use Symfony\Component\Security\Http\Authentication\AuthenticationUtils;
use App\Entity\Utilisateur;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class HomeController extends AbstractController
{
    
    #[Route('/login', name: 'login')]
public function login(Request $request, AuthenticationUtils $authenticationUtils): Response
{
    // Récupérer l'erreur de connexion s'il y en a une
    $error = $authenticationUtils->getLastAuthenticationError();
    // Dernier nom d'utilisateur entré par l'utilisateur
    $lastUsername = $authenticationUtils->getLastUsername();

    // Vérifier si le formulaire de connexion a été soumis
    if ($request->isMethod('POST')) {
        // Récupérer les données soumises par le formulaire
        $email = $request->request->get('email');
        $password = $request->request->get('password');

        // Expression régulière pour valider le format de l'e-mail
        $emailPattern = '/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/';

        // Validation du format de l'e-mail
        if (!preg_match($emailPattern, $email)) {
            // Adresse email invalide
            $this->addFlash('error', 'Adresse e-mail invalide.');
            return $this->redirectToRoute('login');
        }

        // Validation de la longueur minimale du mot de passe
        if (strlen($password) < 8) {
            // Mot de passe trop court
            $this->addFlash('error', 'Le mot de passe doit contenir au moins 8 caractères.');
            return $this->redirectToRoute('login');
        }

        // Vérification si l'e-mail existe dans la base de données
        $userRepository = $this->getDoctrine()->getRepository(Utilisateur::class);
        $user = $userRepository->findOneBy(['email' => $email]);

        if (!$user) {
            // L'e-mail n'existe pas dans la base de données
            $this->addFlash('error', 'Aucun utilisateur trouvé avec cet e-mail. Veuillez créer un compte.');
            return $this->redirectToRoute('user_new'); // Remplacez 'new_user' par le nom de votre route pour la page de création de compte
        }

        // Logique de validation du mot de passe
        // Vous pouvez ajouter des contrôles supplémentaires sur le mot de passe ici

        // Si les contrôles sont réussis, vous pouvez rediriger l'utilisateur vers la page d'accueil ou une autre page appropriée
        return $this->redirectToRoute('home');
    }

    // Afficher le formulaire de connexion avec les éventuelles erreurs
    return $this->render('home/login.html.twig', ['last_username' => $lastUsername, 'error' => $error]);
}

    #[Route('/', name: 'home')]
    public function index(): Response
    {
        // Logique de votre page d'accueil
        return $this->render('home/index.html.twig');
    }
}
