<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="style.css">
    <title>Guess My Number!</title>
</head>
<body>
    <form action="GuessServlet" method="post">
        <header>
            <h1>Guess My Number!</h1>
            <p class="between">(Between 1 and 100)</p>
            <button class="btn again" type="submit" name="action" value="again">Again!</button>
            <div class="number">?</div>
        </header>
        <main>
            <section class="left">
                <input type="number" class="guess" name="guess">
                <button class="btn check" type="submit" name="action" value="guess">Check!</button>
                <input type="hidden" name="attempts" value="0">
                <input type="hidden" name="score" value="0">
                <input type="hidden" name="highscore" value="0">
            </section>
            <section class="right">
        		<p>${message}</p>
        		<br/>
        		<br/>
    			<p>Attempts: ${sessionScope.attempts}</p>
    			<br/>
    			<br/>
    			<p>HighScore: ${sessionScope.highscore}</p>
      		</section>
        </main>
    </form>
</body>
</html>
