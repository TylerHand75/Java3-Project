package FunStuff;

import FunStuff.Message.Message;
import FunStuff.Message.MessageDecoder;
import FunStuff.Message.MessageEncoder;

import javax.websocket.*;

import javax.websocket.Session;
import javax.websocket.server.*;
import java.io.IOException;
import java.util.*;


@ServerEndpoint(
        value = "/tictactoe/endpoint",
        encoders = {MessageEncoder.class},
        decoders = {MessageDecoder.class}
)

public class TicTacToeEndpoint {
    private static final Map<Session, String> players = Collections.synchronizedMap(new HashMap<Session, String>());
    private static int guestCount = 0;

    public static <T,E> T getKeyByValue(Map<T,E> map, E value) {
        for (Map.Entry<T,E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
    private void sendJson(String jsonstr,Session snedTo)throws DecodeException,EncodeException, IOException{
        MessageDecoder decoder = new MessageDecoder();
        if (decoder.willDecode(jsonstr)) {
            Message message = decoder.decode(jsonstr);
            snedTo.getBasicRemote().sendObject(message);
        }
    }


    @OnOpen
    public void onOpen(Session session) throws EncodeException, IOException, DecodeException {
        String jsonStr = "";
        if(!players.containsValue("X")) {
            players.put(session, "X");
            System.out.println("Player X joined the game");
            if(!players.containsValue("O")) {
                jsonStr = "{\"messageType\":\"playerJoined\",\"message\":\"please wait for player 0 to join\",\"player\":\"X\",\"itsYourTurn\":\"false\"}";
            } else {
                jsonStr = "{\"messageType\":\"playerJoined\",\"message\":\"it's your turn\",\"player\":\"X\",\"itsYourTurn\":\"true\"}";
            }
            sendJson(jsonStr, session);
        } else if(!players.containsValue("O")) {
            players.put(session, "O");
            System.out.println("Player O joined the game");
            jsonStr = "{\"messageType\":\"playerJoined\",\"message\":\"please wait\",\"player\":\"0\",\"itsYourTurn\":\"false\"}";
            sendJson(jsonStr, session);
            jsonStr = "{\"messageType\":\"playerJoined\",\"message\":\"it's your turn\",\"player\":\"X\",\"itsYourTurn\":\"true\"}";
            sendJson(jsonStr, getKeyByValue(players, "X"));
        } else {
            players.put(session, "Guest" + ++guestCount);
            System.out.printf("Guest%s joined the game\n", guestCount);
            jsonStr = "{\"messageType\":\"playerJoined\",\"player\":\"Guest\",\"message\":\"You are a guest\",\"itsYourTurn\":\"false\"}";
            sendJson(jsonStr, session);
        }
    }



    @OnClose
    public void onClose(Session session) {
        String playerWhoLeft = players.get(session);
        System.out.println(playerWhoLeft + " left the game");
        players.remove(session);

    }

    @OnMessage
    public void onMessage(Message json, Session session){

    }

    @OnError
    public void onError(Throwable throwable) {
        System.out.println("ERROR: " + throwable.getMessage());

    }




}
