<?php

namespace Task\EventBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Response;

class DefaultController extends Controller
{
    public function indexAction($name, $count)
    {
        return $this->render('EventBundle:Default:index.html.twig', array(
            'name' => $name,
            'count' => $count,
            'content' => 'Your tasks for today:'
        ));
    }//indexAction
}//DefaultController
