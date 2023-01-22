package io.oc.Umpire;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.arguments.ArgumentEntity;
import net.minecraft.commands.arguments.selector.EntitySelector;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.bukkit.Bukkit.getLogger;

public class UmpireArgumentEntity implements InvocationHandler {
    ArgumentEntity argumentEntity;

    public UmpireArgumentEntity(ArgumentEntity argumentEntity) {
        this.argumentEntity = argumentEntity;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        getLogger().info("got invocation of method: " + method.getName());
        return method.invoke(argumentEntity, args);
    }
}
