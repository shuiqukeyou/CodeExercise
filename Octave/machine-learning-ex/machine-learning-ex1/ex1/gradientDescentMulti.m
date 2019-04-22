function [theta, J_history] = gradientDescentMulti(X, y, theta, alpha, num_iters)
%GRADIENTDESCENTMULTI Performs gradient descent to learn theta
%   theta = GRADIENTDESCENTMULTI(x, y, theta, alpha, num_iters) updates theta by
%   taking num_iters gradient steps with learning rate alpha

% Initialize some useful values
m = length(y); % number of training examples
J_history = zeros(num_iters, 1);

for iter = 1:num_iters

    % ====================== YOUR CODE HERE ======================
    % Instructions: Perform a single gradient step on the parameter vector
    %               theta. 
    %
    % Hint: While debugging, it can be useful to print out the values
    %       of the cost function (computeCostMulti) and gradient here.
    %
    n = length(theta)
    m1 = 0;
    m2 = 0;
    m3 = 0;
    for i = 1:m
      h = X(i,:) * theta ;
      m1 = m1 + (h - y(i)) * X(i,1);
      m2 = m2 + (h - y(i)) * X(i,2);
      m3 = m3 + (h - y(i)) * X(i,3);
    endfor
    t1 = theta(1,1) - alpha*m1/m;
    t2 = theta(2,1) - alpha*m2/m;
    t3 = theta(3,1) - alpha*m3/m;
    theta(1,1) = t1;
    theta(2,1) = t2;
    theta(3,1) = t3;


    % ============================================================

    % Save the cost J in every iteration    
    J_history(iter) = computeCostMulti(X, y, theta);

end

end
