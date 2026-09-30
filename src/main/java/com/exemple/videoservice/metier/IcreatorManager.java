package com.exemple.videoservice.metier;

import com.exemple.videoservice.dao.entities.Creator;
import com.exemple.videoservice.dto.CreatorRequest; // À adapter selon votre package

public interface IcreatorManager {
    Creator saveCreator(Creator creator);
    Creator saveCreator(CreatorRequest creatorRequest); // Surcharge pour la Subscription
}