#include <chrono>
#include <ctime>
#include <iostream>
#include <random>
#include <string>
#include <thread>

int getRandomNum()
{
    srand(time(0));
    return rand() % 100 + 1;
}

int playGame()
{
    //  Set up initial values
    int playerGuess = 0, computerNum = 0, totalGuesses = 0;
    computerNum = getRandomNum();
    std::cout << "I'm thinking of a number between 1 and 100..." << std::endl;
    while (playerGuess != computerNum) {
        std::cout << "Enter a number: ";
        if (std::cin >> playerGuess) {
            if (playerGuess > 100 || playerGuess < 1) {
                std::cout << "You do know what \"between 1 and 100\" means, right?" << std::endl;
              }
            else if (playerGuess > computerNum) {
                std::cout << "That number is too big!" << std::endl;
              }
            else if (playerGuess < computerNum) {
                std::cout << "That number is too small!" << std::endl;
              }
          }
        else {
            std::cout << "Dafuq is that?" << std::endl;
            std::cin.clear();
            std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
          }
        totalGuesses++;
      }
    std::cout << "You won! It took you " << totalGuesses << " tries to guess the right number!" << std::endl;
    std::this_thread::sleep_for(std::chrono::milliseconds(2000));
    return 0;
}

int playAgain()
{
    int playAgain = 0;
    std::string playerResponse = "";
    while (playAgain ==  0) {
        std::cout <<  "Would you like to play again? (Yes/No)" <<  std::endl;
        if (std::cin >> playerResponse) {
            std::string::iterator it;
            for (it = playerResponse.begin(); it < playerResponse.end(); it++) {
                *it = std::tolower(*it);
            }
            if (playerResponse == "yes" || playerResponse == "y") {
                playGame();
              }
            else if (playerResponse == "no" || playerResponse == "n") {
                break;
              }
            else {
                std::cout << "wut?" << std::endl;
              }
          }
        else {
            std::cout << "Dafuq is that?" << std::endl;
            std::cin.clear();
            std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
          }
    }
}

int main()
{
    playGame();
    playAgain();
    return 0;
}
