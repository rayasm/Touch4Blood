package com.bloodDonor.ConnectServer;

import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface UserControllerInterface {
 @Put
 void create(User user);

 @Get
 Container getAllUsers();
}
